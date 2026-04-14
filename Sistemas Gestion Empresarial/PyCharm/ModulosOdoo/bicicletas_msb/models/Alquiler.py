from odoo import models, fields, api
from odoo.exceptions import ValidationError


class Alquiler(models.Model):
    _name = "bicicletas_msb.alquiler"

    cliente = fields.Many2one("bicicletas_msb.cliente", string="Cliente", required=True)
    bicicleta = fields.Many2one("bicicletas_msb.bicicleta", string="Bicicleta", required=True)

    fecha_inicio = fields.Date(required=True)
    fecha_fin = fields.Date(required=True)

    estado = fields.Selection([
        ('Activa', 'Activa'),
        ('Cancelada', 'Cancelada')
    ], default="Activa", required=True)

    precio = fields.Float(compute="_compute_precio")

    @api.constrains("fecha_inicio", "fecha_fin")
    def _check_duracion(self):
        for alquiler in self:
            if alquiler.fecha_inicio and alquiler.fecha_fin:
                dias = (alquiler.fecha_fin - alquiler.fecha_inicio).days
                if dias < 1:
                    raise ValidationError("La duración mínima del alquiler debe ser de un día.")

    @api.constrains("bicicleta", "fecha_inicio", "fecha_fin", "estado")
    def _check_solapamiento(self):
        for alquiler in self:
            if alquiler.estado == 'Activa' and alquiler.fecha_inicio and alquiler.fecha_fin:
                solapamientos = self.env['bicicletas_msb.alquiler'].search([
                    ('id', '!=', alquiler.id),
                    ('bicicleta', '=', alquiler.bicicleta.id),
                    ('estado', '=', 'Activa'),
                    ('fecha_inicio', '<=', alquiler.fecha_fin),
                    ('fecha_fin', '>=', alquiler.fecha_inicio)
                ])
                if solapamientos:
                    raise ValidationError(
                        "La bicicleta ya está reservada en un periodo que se solapa con este alquiler.")

    @api.depends("bicicleta", "fecha_inicio", "fecha_fin")
    def _compute_precio(self):
        for alquiler in self:
            if alquiler.bicicleta and alquiler.fecha_inicio and alquiler.fecha_fin:
                dias = (alquiler.fecha_fin - alquiler.fecha_inicio).days
                if dias >= 1:
                    precio_base = alquiler.bicicleta.precio_dia * dias

                    if dias > 10:
                        alquiler.precio = precio_base * 0.80
                    elif dias > 5:
                        alquiler.precio = precio_base * 0.90
                    else:
                        alquiler.precio = precio_base
                else:
                    alquiler.precio = 0.0
            else:
                alquiler.precio = 0.0
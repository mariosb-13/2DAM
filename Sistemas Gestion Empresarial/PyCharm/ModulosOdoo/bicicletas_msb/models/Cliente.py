from odoo import models, fields, api
from odoo.exceptions import ValidationError

class Cliente(models.Model):
    _name = "bicicletas_msb.cliente"
    _rec_name = "nombre_representativo"

    nombre_representativo = fields.Char(compute="_nombre")

    nombre_completo = fields.Char(required=True)
    email = fields.Char()
    telefono = fields.Char()
    dni = fields.Char(required=True)

    alquileres = fields.One2many("bicicletas_msb.alquiler", "cliente", string="Alquileres")

    numero_alquileres = fields.Integer(compute="_compute_analisis_cliente")
    total_gastado = fields.Float(compute="_compute_analisis_cliente")


    @api.depends("nombre_completo", "dni")
    def _nombre(self):
        for cliente in self:
            if cliente.nombre_completo and cliente.dni:
                cliente.nombre_representativo = f"{cliente.nombre_completo} ({cliente.dni})"
            else:
                cliente.nombre_representativo = cliente.nombre_completo

    @api.constrains("email", "telefono")
    def _check_contacto(self):
        for cliente in self:
            if not cliente.email and not cliente.telefono:
                raise ValidationError("Es obligatorio informar el email o el teléfono.")

    @api.depends("alquileres.estado", "alquileres.precio")
    def _compute_analisis_cliente(self):
        for cliente in self:
            alquileres_activos = cliente.alquileres.filtered(lambda a: a.estado == 'Activa')
            cliente.numero_alquileres = len(alquileres_activos)
            cliente.total_gastado = sum(alquileres_activos.mapped('precio'))

    @api.constrains("dni")
    def _check_dni_unico(self):
        for cliente in self:
            if cliente.dni:
                # Buscamos si hay algún otro cliente con este mismo DNI
                duplicados = self.env['bicicletas_msb.cliente'].search([
                    ('dni', '=', cliente.dni),
                    ('id', '!=', cliente.id)
                ])
                if duplicados:
                    raise ValidationError("El DNI debe ser único. Ya existe un cliente con este documento.")
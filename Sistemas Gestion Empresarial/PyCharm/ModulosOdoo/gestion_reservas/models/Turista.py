from odoo import models, fields, api

class Turista(models.Model):
    _name = "msb.gestion_reservas.turista"

    _rec_name = "nombre"
    
    nombre = fields.Char(required=True)
    email = fields.Char(required=True)
    telefono = fields.Char()

    reservas = fields.One2many("msb.gestion_reservas.reserva", "turista_id", string="Reservas")
    numero_reservas = fields.Integer(compute="_compute_numero_reservas")
    numero_reservas_activas = fields.Integer(compute="_compute_numero_reservas")
    numero_reservas_canceladas = fields.Integer(compute="_compute_numero_reservas")
    gastado = fields.Float(compute="_compute_numero_reservas")

    _email_unique = models.UniqueIndex("(email)", "El email debe ser único")

    @api.depends("reservas")
    def _compute_numero_reservas(self):
        for turista in self:
            turista.numero_reservas = len(turista.reservas)
            turista.numero_reservas_activas = 0
            turista.numero_reservas_canceladas = 0
            turista.gastado = 0
            for reserva in turista.reservas:
                if reserva.estado == "1":
                    turista.numero_reservas_activas +=1
                    turista.gastado += reserva.actividad_id.precio*reserva.plazas_reservadas
                else:
                    turista.numero_reservas_canceladas +=1
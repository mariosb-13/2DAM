from odoo import models, fields, api


class Turista(models.Model):
    _name = "msb.gestor.turista"
    _description = "Turista"
    _rec_name = "nombre"

    nombre=fields.Char(string="Nombre", required=True)
    email=fields.Char(string="Email", required=True)
    telefono = fields.Char(string="Teléfono")

    reserva_ids = fields.One2many("msb.gestor.reserva", "turista_id", string="Reservas")

    total_reservas = fields.Integer(compute="_compute_estadisticas", store=True)
    reservas_activas = fields.Integer(compute="_compute_estadisticas", store=True)
    reservas_canceladas= fields.Integer(compute="_compute_estadisticas", store=True)
    gasto_total= fields.Float(compute="_compute_estadisticas", store=True)

    _sql_constraints = [
        ('email_uniq', 'unique(email)', 'El email del turista debe ser unico.')
    ]

    @api.depends('reserva_ids', 'reserva_ids.estado')
    def _compute_estadisticas(self):
        for rec in self:
            reservas = rec.reserva_ids
            rec.total_reservas=len(reservas)
            rec.reservas_activas = len(reservas.filtered(lambda r:r.estado == 'activada'))
            rec.reservas_canceladas = len(reservas.filtered(lambda r:r.estado =='cancelada'))
            rec.gasto_total = sum(
                r.actividad_id.precio * r.plazas_reservadas for r in reservas if r.estado == 'activada')
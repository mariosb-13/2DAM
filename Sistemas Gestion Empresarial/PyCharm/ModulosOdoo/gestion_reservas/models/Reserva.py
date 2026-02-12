from odoo import models, fields, api
from odoo.exceptions import ValidationError

class Reserva(models.Model):
    _name = "msb.gestion_reservas.reserva"
    
    turista_id = fields.Many2one("msb.gestion_reservas.turista", string="Turista", required=True)
    actividad_id = fields.Many2one("msb.gestion_reservas.actividad", string="Actividad", required=True)
    plazas_reservadas = fields.Integer(required=True)
    fecha_compra = fields.Date(default=fields.Date.today())
    estado = fields.Selection([("0", "Cancelada"), ("1", "Activada")],default="1")



    @api.constrains('fecha_compra,plazas_reservadas')
    def _check_fecha(self):
        for record in self:
            if record.fecha < record.actividad_id.fecha:
                raise ValidationError("La fecha debe ser futura (api)")
            if record.plazas_reservadas < record.actividad_id.plazas_disponibles:
                raise ValidationError("La fecha debe ser futura (api)")
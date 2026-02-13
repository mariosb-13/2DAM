from odoo import models, fields, api
from odoo.exceptions import ValidationError


class Actividad(models.Model):
    _name = "msb.gestion_reservas.comercial"

    _rec_name = "nombre_apellidos"

    nombre_apellidos = fields.Char(required=True)
    email = fields.Char(required=True)
    telf = fields.Integer(required=True)
    fecha_alta = fields.Date(default=fields.Date.today())
    estado = fields.Selection([("0", "Alta"), ("1", "Baja")], required=True,default="0")

    comercial_id = fields.Many2many("msb.gestion_reservas.reserva", string="Reserva", required=True)


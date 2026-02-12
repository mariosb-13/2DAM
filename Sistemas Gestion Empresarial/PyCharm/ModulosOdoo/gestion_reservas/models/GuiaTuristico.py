from odoo import models, fields, api

class GuiaTuristico(models.Model):
    _name = "msb.gestion_reservas.guia_turistico"

    _rec_name = "nombre"
    
    nombre = fields.Char(required=True)
    idioma = fields.Char(required=True)
    telefono = fields.Char()
    imagen = fields.Image()
from odoo import models, fields

class Guia(models.Model):
    _name = "msb.gestor.guia"
    _description = "Guía Turístico"
    _rec_name = "nombre"

    nombre = fields.Char(string="Nombre", required=True)
    idioma = fields.Char(string="Idioma Principal", required=True)
    telefono = fields.Char(string="Teléfono")
    imagen = fields.Binary(string="Imagen")
from odoo import models, fields


class Categoria(models.Model):
    _name = "msb.lista_categorias.categoria"

    _rec_name = "nombre"
    nombre = fields.Char()
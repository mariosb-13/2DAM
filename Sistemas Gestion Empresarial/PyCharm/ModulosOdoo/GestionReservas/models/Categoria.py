from odoo import models, fields

class Categoria(models.Model):
    _name = "msb.gestor.categoria"
    _description = "Categoría de Actividad"
    _rec_name = "nombre"

    nombre = fields.Char(string="Nombre", required=True)
    descripcion = fields.Text(string="Descripción")
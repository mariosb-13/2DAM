from odoo import models, fields

class EstatePropertyTag(models.Model):
    _name = "estate.property.tag"
    _description = "Etiqueta de Propiedad"
    _order = "name"

    name = fields.Char(required=True)
    color = fields.Integer()

    _sql_constraints = [
        ('name_uniq', 'unique (name)', 'El nombre de la etiqueta debe ser único.')
    ]
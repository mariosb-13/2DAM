from odoo import models, fields

class EstatePropertyType(models.Model):
    _name = "estate.property.type"
    _description = "Tipo de Propiedad"
    _order = "name"

    name = fields.Char(required=True)

    _sql_constraints = [
        ('name_uniq', 'unique (name)', 'El nombre del tipo de propiedad debe ser único.')
    ]
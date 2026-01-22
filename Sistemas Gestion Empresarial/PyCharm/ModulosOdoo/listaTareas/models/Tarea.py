from odoo import models, fields


class Tarea(models.Model):
    _name = "msb.lista_tareas.tarea"

    tarea = fields.Char()
    prioridad = fields.Integer()
    realizada = fields.Boolean()


from odoo import models, fields, api


class Tarea(models.Model):
    _name = "msb.lista_tareas.tarea"

    _rec_name = "tarea"
    tarea = fields.Char(required=True)
    prioridad = fields.Integer()
    realizada = fields.Boolean()
    urgente = fields.Boolean(compute="_urgencia")
    categoria_id = fields.Many2one("msb.lista_categorias.categoria", string="Categoría")

    @api.depends("prioridad")
    def _urgencia(self):
        for tarea in self:
            if tarea.prioridad > 10:
                tarea.urgente = True
            else:
                tarea.urgente = False

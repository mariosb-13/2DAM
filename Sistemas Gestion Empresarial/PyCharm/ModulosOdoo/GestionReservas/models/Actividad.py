from odoo import models, fields, api


class Actividad(models.Model):
    _name = "msb.gestor.actividad"
    _description = "Actividad Turistica"
    _rec_name = "titulo"

    titulo = fields.Char(string="Titulo", required=True)
    fecha = fields.Date(string="Fecha", required=True)
    precio = fields.Float(string="Precio", required=True)
    plazas_totales = fields.Integer(string="Plazas Totales", required=True)
    plazas_disponibles = fields.Integer(string="Plazas Disponibles", required=True)

    categoria_id = fields.Many2one('msb.gestor.categoria', string="Categoría", required=True)

    guia_id = fields.Many2one('msb.gestor.guia', string="Guía Turístico")
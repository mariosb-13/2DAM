from odoo import models, fields, api

class CategoriaActividad(models.Model):
    _name = "msb.gestion_reservas.categoria_actividad"

    _rec_name = "nombre"
    
    nombre = fields.Char(required=True)
    descripcion = fields.Text()
    actividades=fields.One2many("msb.gestion_reservas.actividad","categoria_id",string="Lista de Actividades")
    #nuevo 1
    cantidad_actividades=fields.Integer(compute="_calcular_cantidad", store=True)
    #nuevo 2
    total_ingresado=fields.Float(compute="_calcular_cantidad")


    #nuevo 1y2
    @api.depends("actividades")
    def _calcular_cantidad(self):
        for categoria in self:
           categoria.cantidad_actividades=len(categoria.actividades)
           categoria.total_ingresado=0
           for actividad in categoria.actividades:
               plaza_reservadas = actividad.plazas_totales-actividad.plazas_disponibles
               categoria.total_ingresado+=actividad.precio*plaza_reservadas
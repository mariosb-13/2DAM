from odoo import models, fields, api
from odoo.exceptions import ValidationError

class Actividad(models.Model):
    _name = "msb.gestion_reservas.actividad"
    
    _rec_name = "nombre_representativo"

    #Nuevo
    nombre_representativo=fields.Char(compute="_nombre")

    titulo = fields.Char(required=True)
    fecha = fields.Date(required=True)
    precio = fields.Float(required=True)
    plazas_totales = fields.Integer(required=True)

    #Nuevo
    estado=fields.Char(compute="_calcular_estado")

    categoria_id = fields.Many2one("msb.gestion_reservas.categoria_actividad", string="Categoría", required=True)
    guia_turistico = fields.Many2one("msb.gestion_reservas.guia_turistico", string="Guía turístico")
    
    plazas_disponibles = fields.Integer(compute="_compute_plazas_disponibles")

    reservas = fields.One2many("msb.gestion_reservas.reserva", "actividad_id", string="Reservas")

    _actividad_unica = models.UniqueIndex("(titulo, fecha)", "El título debe ser único")
    _precio_positivo = models.Constraint("CHECK(precio >= 0)","El precio no puede ser negativo")
    _plazas_totales_positivo = models.Constraint("CHECK(plazas_totales >= 0)","Las plazas totales tiene que ser positivo")
    _plazas_disponibles_positivo = models.Constraint("CHECK(plazas_disponibles >= 0 AND plazas_disponibles <= plazas_totales)","Las plazas disponibles tiene que ir entre 0 y las plazas totales")
    #_fecha_futura = models.Constraint("CHECK(fecha > current_date)","La fecha debe ser futura")

  #  @api.constrains('fecha')
   # def _check_fecha(self):
    #    for record in self:
     #       if record.fecha < fields.Date.today():
      #          raise ValidationError("La fecha debe ser futura (api)")


    @api.depends("plazas_totales", "reservas")
    def _compute_plazas_disponibles(self):
        for actividad in self:
            actividad.plazas_disponibles = actividad.plazas_totales
            for reserva in actividad.reservas:
                if reserva.estado == "1":
                    actividad.plazas_disponibles -= reserva.plazas_reservadas

    @api.depends("titulo", "fecha")
    def _nombre(self):
        for actividad in self:
            actividad.nombre_representativo=f'{actividad.titulo}({actividad.fecha})'
            #actividad.nombre_representativo=actividad.titulo+" ("+str(actividad.fecha)+")"

    @api.depends("fecha")
    def _calcular_estado(self):
        for actividad in self:
            if actividad.fecha:
                if actividad.fecha>fields.Date.today():
                    actividad.estado="Futura"
                else:
                    actividad.estado="Pasada"
            else:
                actividad.estado="Pendiente"
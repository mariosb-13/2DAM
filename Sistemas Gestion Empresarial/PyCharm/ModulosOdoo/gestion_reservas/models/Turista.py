from odoo import models, fields, api

class Turista(models.Model):
    _name = "msb.gestion_reservas.turista"

    _rec_name = "nombre_representativo"

    #Nombre y email
    nombre_representativo=fields.Char(compute="_nombre")

    
    nombre = fields.Char(required=True)
    email = fields.Char(required=True)
    telefono = fields.Char()

    reservas = fields.One2many("msb.gestion_reservas.reserva", "turista_id", string="Reservas")
    numero_reservas = fields.Integer(compute="_compute_numero_reservas")
    numero_reservas_activas = fields.Integer(compute="_compute_numero_reservas")
    numero_reservas_canceladas = fields.Integer(compute="_compute_numero_reservas")
    gastado = fields.Float(compute="_compute_numero_reservas")
    VIP = fields.Boolean(compute="_compute_VIP")

    _email_unique = models.UniqueIndex("(email)", "El email debe ser único")

    @api.depends("reservas")
    def _compute_numero_reservas(self):
        for turista in self:
            turista.numero_reservas = len(turista.reservas)
            turista.numero_reservas_activas = 0
            turista.numero_reservas_canceladas = 0
            turista.gastado = 0
            for reserva in turista.reservas:
                if reserva.estado == "1":
                    turista.numero_reservas_activas +=1
                    turista.gastado += reserva.actividad_id.precio*reserva.plazas_reservadas
                else:
                    turista.numero_reservas_canceladas +=1

    @api.depends("nombre", "email")
    def _nombre(self):
        for turista in self:
            turista.nombre_representativo = f'{turista.nombre}({turista.email})'

    @api.depends("gastado")
    def _compute_VIP(self):
        for turista in self:
            if turista.gastado>1000:
                turista.VIP=True
            else:
                turista.VIP=False
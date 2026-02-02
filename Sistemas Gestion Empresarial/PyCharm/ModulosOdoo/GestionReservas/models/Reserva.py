from odoo import fields, api, models
from odoo.exceptions import UserError, ValidationError


class Reserva(models.Model):
    _name = 'msb.gestor.reserva'
    _description = 'Reserva de Actividad'

    turista_id = fields.Many2one('msb.gestor.turista', string='Turista', required=True)
    actividad_id = fields.Many2one('msb.gestor.actividad', string='Actividad', required=True)
    plazas_reservadas = fields.Integer(string='Plazas', required=True, default=1)
    fecha_compra = fields.Date(string='Fecha Compra', default=fields.Date.today, required=True)

    estado = fields.Selection([
        ('activada', 'Activada'),
        ('cancelada', 'Cancelada')
    ], string='Estado', default='activada', required=True, readonly=True)

    @api.model_create_multi
    def create(self, vals_list):
        for vals in vals_list:
            if 'actividad_id' in vals:
                actividad = self.env['msb.gestor.actividad'].browse(vals['actividad_id'])
                plazas = vals.get('plazas_reservadas', 1)

                if actividad.fecha and actividad.fecha <= fields.Date.today():
                    raise UserError("Solo se pueden reservar actividades futuras.")

                if actividad.plazas_disponibles < plazas:
                    raise UserError("No hay suficientes plazas disponibles.")

                actividad.plazas_disponibles -= plazas

        return super(Reserva, self).create(vals_list)

    def write(self, vals):
        if 'estado' in vals and vals['estado'] == 'activada':
            for rec in self:
                if rec.estado == 'cancelada':
                    raise UserError("No se puede reactivar una reserva cancelada.")
        return super(Reserva, self).write(vals)

    def unlink(self):
        for rec in self:
            if rec.estado != 'cancelada':
                raise UserError("Las reservas no se pueden borrar, debe cancelarlas.")
        return super(Reserva, self).unlink()

    def action_cancelar(self):
        for rec in self:
            if rec.actividad_id.fecha <= fields.Date.today():
                raise UserError("Solo se pueden cancelar las reservas de actividades futuras.")

            if rec.estado == 'activada':
                rec.estado = 'cancelada'

                rec.actividad_id.plazas_disponibles += rec.plazas_reservadas
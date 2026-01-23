from odoo import models, fields, api
from odoo.exceptions import UserError


class EstatePropertyOffer(models.Model):
    _name = "estate.property.offer"
    _description = "Real Estate Property Offer"
    _order = "price desc"

    price = fields.Float()
    status = fields.Selection(
        selection=[('accepted', 'Accepted'), ('refused', 'Refused')],
        copy=False
    )
    partner_id = fields.Many2one("res.partner", required=True)
    property_id = fields.Many2one("estate.property", required=True)

    # --- VALIDACIÓN (No puedes aceptar una oferta menor que otra existente) ---
    _sql_constraints = [
        ('check_price', 'CHECK(price > 0)', 'The offer price must be strictly positive.')
    ]

    # --- ACCIONES DE LOS BOTONES ---
    def action_accept(self):
        for record in self:
            if record.property_id.offer_ids.filtered(lambda o: o.status == 'accepted'):
                raise UserError("Solo puedes aceptar una oferta por propiedad.")

            record.status = 'accepted'
            # AQUÍ OCURRE LA MAGIA: Al escribir selling_price, saltará tu restricción del 90%
            record.property_id.selling_price = record.price
            record.property_id.buyer_id = record.partner_id
        return True

    def action_refuse(self):
        for record in self:
            record.status = 'refused'
        return True
from odoo import models, fields, api
from odoo.exceptions import UserError, ValidationError  # <--- IMPORTANTE: Importar ValidationError
from odoo.tools import float_compare, float_is_zero  # <--- Para comparar precios correctamente
from dateutil.relativedelta import relativedelta


class EstateProperty(models.Model):
    _name = "estate.property"
    _description = "Real Estate Property"
    _order = "id desc"

    # --- SQL CONSTRAINTS (NUEVO CAP 10) ---
    _sql_constraints = [
        # HE CAMBIADO EL NOMBRE AQUI A _v2 PARA FORZAR LA ACTUALIZACION
        ('check_expected_price_v2', 'CHECK(expected_price > 0)',
         'El precio esperado debe ser estrictamente positivo.'),
        ('check_selling_price', 'CHECK(selling_price >= 0)',
         'El precio de venta debe ser positivo.')
    ]

    # --- CAMPOS ---
    name = fields.Char(string="Title", required=True)
    description = fields.Text()
    postcode = fields.Char()
    date_availability = fields.Date(
        string="Available From",
        copy=False,
        default=lambda self: fields.Date.today() + relativedelta(months=3)
    )
    expected_price = fields.Float(required=True)
    selling_price = fields.Float(readonly=True, copy=False)
    bedrooms = fields.Integer(default=2)
    living_area = fields.Integer(string="Living Area (sqm)")
    facades = fields.Integer()
    garage = fields.Boolean()
    garden = fields.Boolean()
    garden_area = fields.Integer()
    garden_orientation = fields.Selection(
        selection=[
            ('north', 'North'),
            ('south', 'South'),
            ('east', 'East'),
            ('west', 'West')
        ],
        help="Orientation of the garden"
    )
    active = fields.Boolean(default=True)
    state = fields.Selection(
        selection=[
            ('new', 'New'),
            ('offer_received', 'Offer Received'),
            ('offer_accepted', 'Offer Accepted'),
            ('sold', 'Sold'),
            ('canceled', 'Canceled')
        ],
        required=True,
        copy=False,
        default='new',
        string="Status"
    )

    # --- RELACIONES ---
    property_type_id = fields.Many2one("estate.property.type", string="Type")
    buyer_id = fields.Many2one("res.partner", string="Buyer", copy=False)
    salesperson_id = fields.Many2one("res.users", string="Salesperson", default=lambda self: self.env.user)
    tag_ids = fields.Many2many("estate.property.tag", string="Tags")
    offer_ids = fields.One2many("estate.property.offer", "property_id", string="Offers")

    # --- CAMPOS CALCULADOS ---
    total_area = fields.Integer(compute="_compute_total_area", string="Total Area (sqm)")
    best_price = fields.Float(compute="_compute_best_price", string="Best Offer")

    # --- PYTHON CONSTRAINTS (NUEVO CAP 10) ---
    @api.constrains('selling_price', 'expected_price')
    def _check_selling_price(self):
        for record in self:
            # Si el precio de venta es cero (no vendida), no comprobamos nada
            if float_is_zero(record.selling_price, precision_digits=2):
                continue

            # Calculamos el 90% del precio esperado
            price_limit = record.expected_price * 0.9

            # Comparamos: Si precio venta < 90% esperado
            if float_compare(record.selling_price, price_limit, precision_digits=2) == -1:
                # Permitimos excepciones si hay una oferta aceptada manualmente (lógica opcional pero recomendada)
                # Pero siguiendo el tutorial estricto, lanzamos error:
                raise ValidationError(
                    "El precio de venta no puede ser inferior al 90% del precio esperado. "
                    "¡Intenta aumentar la oferta o bajar el precio esperado!"
                )

    # --- MÉTODOS COMPUTE ---
    @api.depends("living_area", "garden_area")
    def _compute_total_area(self):
        for record in self:
            record.total_area = record.living_area + record.garden_area

    @api.depends("offer_ids.price")
    def _compute_best_price(self):
        for record in self:
            if record.offer_ids:
                record.best_price = max(record.offer_ids.mapped("price"))
            else:
                record.best_price = 0.0

    # --- MÉTODOS ONCHANGE ---
    @api.onchange("garden")
    def _onchange_garden(self):
        if self.garden:
            self.garden_area = 10
            self.garden_orientation = "north"
        else:
            self.garden_area = 0
            self.garden_orientation = False

    # --- ACCIONES ---
    def action_sold(self):
        for record in self:
            if record.state == 'canceled':
                raise UserError("Canceled properties cannot be sold.")
            record.state = 'sold'
        return True

    def action_cancel(self):
        for record in self:
            if record.state == 'sold':
                raise UserError("Sold properties cannot be canceled.")
            record.state = 'canceled'
        return True
const express = require('express');
const router = express.Router();
const fs = require('fs');
const db = require('../db.json');

router.get('/', (req, res) => {
    res.json(db.orders);
});

router.get('/:id', (req, res) => {
    const order = db.orders.find(order => order.id === parseInt(req.params.id));
    if (!order) {
        return res.status(404).json({ message: 'Order not found' });
    }
    res.json(order);
});

router.post('/', (req, res) => {
    const { customer_id, product_id, quantity } = req.body;

    if (!customer_id || !product_id || !quantity) {
        return res.status(400).json({ message: 'Missing required fields' });
    }

    const order = {
        id: db.orders.length + 1,
        customer_id,
        product_id,
        quantity,
    };
    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
        if (err) return console.log(err);
        console.log('writing to ' + fileName);
    })
    db.orders.push(order);
    res.status(201).json(order);
});

module.exports = router;

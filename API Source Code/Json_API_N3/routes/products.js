const express = require('express');
const router = express.Router();
const fs = require('fs');
const db = require('../db.json');

router.get('/', (req, res) => {
    res.json(db.products);
});

router.get('/:id', (req, res) => {
    const product = db.products.find(product => product.id === parseInt(req.params.id));
    if (!product) {
        return res.status(404).json({ message: 'Product not found' });
    }
    res.json(product);
});

router.post('/', (req, res) => {
    const { name, price, categoryId, manufacturerId } = req.body;

    if (!name || !price || !categoryId || !manufacturerId) {
        return res.status(400).json({ message: 'Missing required fields' });
    }

    const newProduct = {
        id: db.products.length + 1,
        name,
        price,
        categoryId,
        manufacturerId
    };
    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
        if (err) return console.log(err);
        console.log('writing to ' + fileName);
    })
    db.products.push(newProduct);
    res.status(201).json(newProduct);
});

module.exports = router;

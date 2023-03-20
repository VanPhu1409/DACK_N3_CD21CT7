const express = require('express');
const router = express.Router();
const db = require('../db.json');

router.get('/', (req, res) => {
    res.json(db.manufacturers);
});

router.get('/:id', (req, res) => {
    const manufacturer = db.manufacturers.find(manufacturer => manufacturer.id === parseInt(req.params.id));
    if (!manufacturer) {
        return res.status(404).json({ message: 'Manufacturer not found' });
    }
    res.json(manufacturer);
});

router.post('/', (req, res) => {
    const { name, country } = req.body;
    if (!name || !country) {
        return res.status(400).json({ message: 'Name and country are required' });
    }
    const id = db.manufacturers.length + 1;
    const newManufacturer = {
        id,
        name,
        country
    };
    db.manufacturers.push(newManufacturer);
    res.status(201).json(newManufacturer);
});

router.patch('/:id', (req, res) => {
    const manufacturer = db.manufacturers.find(manufacturer => manufacturer.id === parseInt(req.params.id));
    if (!manufacturer) {
        return res.status(404).json({ message: 'Manufacturer not found' });
    }
    const { name, country } = req.body;
    if (!name || !country) {
        return res.status(400).json({ message: 'Name and country are required' });
    }
    manufacturer.name = name;
    manufacturer.country = country;
    res.json(manufacturer);
});

router.delete('/:id', (req, res) => {
    const index = db.manufacturers.findIndex(manufacturer => manufacturer.id === parseInt(req.params.id));
    if (index === -1) {
        return res.status(404).json({ message: 'Manufacturer not found' });
    }
    db.manufacturers.splice(index, 1);
    res.sendStatus(204);
});

module.exports = router;

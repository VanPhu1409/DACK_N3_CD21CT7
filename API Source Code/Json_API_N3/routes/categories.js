const express = require('express');
const router = express.Router();
const db = require('../db.json');

router.get('/', (req, res) => {
    res.json(db.categories);
});

router.get('/:id', (req, res) => {
    const category = db.categories.find(category => category.id === parseInt(req.params.id));
    if (!category) {
        return res.status(404).json({ message: 'Category not found' });
    }
    res.json(category);
});

router.post('/', (req, res) => {
    const { name } = req.body;

    if (!name) {
        return res.status(400).json({ message: 'Missing required fields' });
    }

    const newCategory = {
        id: db.categories.length + 1,
        name
    };
    db.categories.push(newCategory);
    res.status(201).json(newCategory);
});

module.exports = router;

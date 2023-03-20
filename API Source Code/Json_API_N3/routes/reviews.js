const express = require('express');
const router = express.Router();
const fs = require('fs');
const db = require('../db.json');

router.get('/', (req, res) => {
    res.json(db.reviews);
});
''
router.get('/:id', (req, res) => {
    const review = db.reviews.find(review => review.id === parseInt(req.params.id));
    if (!review) {
        return res.status(404).json({ message: 'Review not found' });
    }
    res.json(review);
});

router.post('/', (req, res) => {
    const { product_id, customer_id, rating, comment } = req.body;

    if (!product_id || !customer_id || !rating) {
        return res.status(400).json({ message: 'Missing required fields' });
    }

    const review = {
        id: db.reviews.length + 1,
        product_id,
        customer_id,
        rating,
        comment: comment || '',
    };
    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
        if (err) return console.log(err);
        console.log('writing to ' + fileName);
    })
    db.reviews.push(review);
    res.status(201).json(review);
});

module.exports = router;

const express = require('express');
const fs = require('fs');
const router = express.Router();
const db = require('../db.json');

router.get('/:id', (req, res) => {
    const user = db.users.find(user => user.id === parseInt(req.params.id));
    if (!user) {
        return res.status(404).json({ message: 'User not found' });
    }
    res.json(user);
});

router.post('/', (req, res) => {
    const { name, email, password } = req.body;

    if (!name || !email || !password) {
        return res.status(400).json({ message: 'Missing required fields' });
    }

    if (db.users.some(user => user.email === email)) {
        return res.status(409).json({ message: 'Email already in use' });
    }

    const newUser = {
        id: db.users.length + 1,
        name,
        email,
        password
    };
    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
        if (err) return console.log(err);
        console.log('writing to ' + fileName);
    })
    db.users.push(newUser);
    res.status(201).json(newUser);
});

module.exports = router;

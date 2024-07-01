// index.js

const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const PORT = process.env.PORT || 8080; // Port entweder aus der Umgebungsvariable oder 8080

const app = express();

// Middleware
app.use(cors());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// Routes
const beitragRouter = require('./routes/beitrag'); // Beispielroute, entsprechend deiner Implementierung anpassen
app.use('/api/beiträge', beitragRouter);

// Start server
app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});

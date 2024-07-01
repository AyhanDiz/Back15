// index.js

const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors'); // Stelle sicher, dass cors hier importiert wird
const PORT = process.env.PORT || 8080;

const app = express();

// Middleware
app.use(cors()); // CORS aktivieren
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// Weitere Konfiguration und Routen...

// Start server
app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});

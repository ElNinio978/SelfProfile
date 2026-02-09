require("dotenv").config();

const express = require("express");
const cors = require("cors");
const mongoose = require("mongoose");

const app = express();
const port = 8080;

// middleware
app.use(cors());
app.use(express.json());

// ====== MONGODB ======
mongoose.connect(process.env.MONGO_URI)
  .then(() => console.log("MongoDB connected"))
  .catch(err => console.error(err));



// ====== MODEL ======
const profileSchema = new mongoose.Schema({
    fullName: String,
    username: String,
    bio: String
});

const Profile = mongoose.model("Profile", profileSchema);

// ====== ENDPOINTY ======

// GET /hello
app.get("/hello", async (req, res) => {
    let profile = await Profile.findOne();

    if (!profile) {
        profile = new Profile({
            fullName: "Jan Kowalski",
            username: "jankow",
            bio: "Miłośnik kotów i kodu"
        });
        await profile.save();
    }

    res.json(profile);
});

// POST /updateProfile
app.post("/updateProfile", async (req, res) => {
    const { fullName, username, bio } = req.body;

    let profile = await Profile.findOne();

    if (!profile) {
        profile = new Profile({});
    }

    if (fullName) profile.fullName = fullName;
    if (username) profile.username = username;
    if (bio) profile.bio = bio;

    await profile.save();

    res.json({
        message: "Profil zaktualizowany",
        profile
    });
});

// GET /
app.get("/", (req, res) => {
    res.send("API działa! Odwiedź /hello, żeby zobaczyć profil.");
});

// ====== START ======
app.listen(port, () => {
    console.log(`Server running at http://localhost:${port}`);
});

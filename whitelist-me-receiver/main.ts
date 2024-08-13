import express from "express";
import {spawn} from "child_process";

const app = express();
const port = process.env.PORT || 3020;

app.get("/:minecraftId", (req, res) => {
    const id = req.params["minecraftId"]
    const rcon = spawn("rcon-cli", ["whitelist", "add", id])

    rcon.stdout.on("data", (data) => {
        console.log(`stdout: ${data}`);
    })

    rcon.stderr.on("data", (data) => {
        console.log(`stderr: ${data}`);
    })

    rcon.on("close", (code) => {
        console.log(`rcon-cli exited with code ${code}`);
        if (code === 0) {
            res.send("added")
        } else {
            res.status(500).send("failed")
        }
    })
})

app.listen(port, () => {
    console.log(`Server is running on port ${port}`);
})
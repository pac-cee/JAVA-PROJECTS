import express from "express";
import cors from "cors";
import dotenv from "dotenv";
import convertRouter from "./routes/convert";

dotenv.config();

const app = express();
const PORT = process.env.PORT || 4000;

app.use(cors());
app.use(express.json());

app.use("/api/convert", convertRouter);

app.get("/", (req, res) => {
  res.send("Crypto Converter API is running.");
});

app.listen(PORT, () => {
  console.log(`Server listening on port ${PORT}`);
});

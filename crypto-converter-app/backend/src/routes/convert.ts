import express from "express";
import axios from "axios";

const router = express.Router();

// Example: /api/convert?crypto=btc&currencies=usd,ngn,rwf
router.get("/", async (req, res) => {
  const { crypto = "btc", currencies = "usd,ngn" } = req.query;
  try {
    const url = `https://api.coingecko.com/api/v3/simple/price?ids=${crypto}&vs_currencies=${currencies}`;
    const response = await axios.get(url);
    res.json({
      success: true,
      data: response.data
    });
  } catch (error) {
    res.status(500).json({ success: false, error: "Failed to fetch conversion rates." });
  }
});

export default router;

# Crypto Converter Backend

A simple Express.js + TypeScript backend to fetch cryptocurrency conversion rates using the CoinGecko API.

## Setup

```
npm install
npm run dev
```

## API Endpoint

- `/api/convert?crypto=btc&currencies=usd,ngn,rwf`

Returns real-time conversion rates for the specified cryptocurrency to the given fiat currencies.

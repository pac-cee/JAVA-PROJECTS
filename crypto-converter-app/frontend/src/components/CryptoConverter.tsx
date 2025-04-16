import React, { useState } from 'react';
import axios from 'axios';

const CRYPTO_OPTIONS = [
  { id: 'bitcoin', label: 'Bitcoin (BTC)' },
  { id: 'ethereum', label: 'Ethereum (ETH)' },
  { id: 'litecoin', label: 'Litecoin (LTC)' },
  { id: 'dogecoin', label: 'Dogecoin (DOGE)' },
  // Add more as needed
];

const CURRENCY_OPTIONS = [
  { code: 'usd', label: 'US Dollar (USD)' },
  { code: 'ngn', label: 'Nigerian Naira (NGN)' },
  { code: 'rwf', label: 'Rwandan Franc (RWF)' },
  { code: 'eur', label: 'Euro (EUR)' },
  { code: 'gbp', label: 'British Pound (GBP)' },
  { code: 'jpy', label: 'Japanese Yen (JPY)' },
  // Add more as needed
];

const CryptoConverter: React.FC = () => {
  const [crypto, setCrypto] = useState('bitcoin');
  const [currency, setCurrency] = useState('usd');
  const [result, setResult] = useState<string | null>(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const handleConvert = async () => {
    setLoading(true);
    setError(null);
    setResult(null);
    try {
      const res = await axios.get(`/api/convert`, {
        params: {
          crypto,
          currencies: currency,
        },
      });
      const price = res.data.data[crypto]?.[currency];
      if (price !== undefined) {
        setResult(
          `1 ${CRYPTO_OPTIONS.find(c => c.id === crypto)?.label} = ${price} ${CURRENCY_OPTIONS.find(c => c.code === currency)?.label}`
        );
      } else {
        setError('No data found for selected pair.');
      }
    } catch (e) {
      setError('Failed to fetch conversion rate.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="bg-white rounded shadow-lg p-8 w-full max-w-md">
      <div className="mb-4">
        <label className="block mb-1 font-medium">Cryptocurrency:</label>
        <select
          className="w-full p-2 border rounded"
          value={crypto}
          onChange={e => setCrypto(e.target.value)}
        >
          {CRYPTO_OPTIONS.map(option => (
            <option key={option.id} value={option.id}>{option.label}</option>
          ))}
        </select>
      </div>
      <div className="mb-4">
        <label className="block mb-1 font-medium">Currency:</label>
        <select
          className="w-full p-2 border rounded"
          value={currency}
          onChange={e => setCurrency(e.target.value)}
        >
          {CURRENCY_OPTIONS.map(option => (
            <option key={option.code} value={option.code}>{option.label}</option>
          ))}
        </select>
      </div>
      <button
        className="w-full bg-blue-600 text-white font-semibold py-2 rounded hover:bg-blue-700 transition"
        onClick={handleConvert}
        disabled={loading}
      >
        {loading ? 'Converting...' : 'Convert'}
      </button>
      {result && <div className="mt-4 text-green-700 font-semibold">{result}</div>}
      {error && <div className="mt-4 text-red-600">{error}</div>}
    </div>
  );
};

export default CryptoConverter;

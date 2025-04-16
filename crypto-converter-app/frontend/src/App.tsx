import React from 'react';
import CryptoConverter from './components/CryptoConverter';

function App() {
  return (
    <div className="min-h-screen bg-gray-100 flex flex-col items-center justify-center p-4">
      <h1 className="text-3xl font-bold mb-6 text-blue-700">Crypto Converter</h1>
      <CryptoConverter />
      <footer className="mt-10 text-gray-500 text-xs">Powered by CoinGecko API</footer>
    </div>
  );
}

export default App;

import React, { useEffect, useState } from 'react';

function App() {
  const [price, setPrice] = useState(null);
  const [prevPrice, setPrevPrice] = useState(null);
  const [change, setChange] = useState(null);

  useEffect(() => {
    const ws = new WebSocket('wss://stream.binance.com:9443/ws/btcusdt@trade');
    ws.onmessage = (event) => {
      const data = JSON.parse(event.data);
      const newPrice = parseFloat(data.p);
      setPrevPrice(price);
      setPrice(newPrice);
      if (price !== null) {
        setChange(newPrice - price);
      }
    };
    return () => ws.close();
    // eslint-disable-next-line
  }, [price]);

  return (
    <div style={{ textAlign: 'center', marginTop: '10vh', fontFamily: 'Arial' }}>
      <h1>BTC/USDT Real-Time Price (Binance WS)</h1>
      <div style={{ fontSize: '3rem', color: change > 0 ? 'green' : change < 0 ? 'red' : 'black' }}>
        {price !== null ? `$${price.toLocaleString()}` : 'Loading...'}
      </div>
      {change !== null && (
        <div style={{ fontSize: '1.5rem', color: change > 0 ? 'green' : 'red' }}>
          {change > 0 ? '+' : ''}{change?.toFixed(2)}
        </div>
      )}
    </div>
  );
}

export default App;

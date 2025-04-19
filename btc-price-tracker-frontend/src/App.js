import React, { useEffect, useState } from 'react';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

function App() {
  const [price, setPrice] = useState(null);
  const [prevPrice, setPrevPrice] = useState(null);
  const [change, setChange] = useState(null);

  useEffect(() => {
    const client = new Client({
      webSocketFactory: () => new SockJS('http://localhost:8081/ws-btc'),
      reconnectDelay: 5000,
      onConnect: () => {
        client.subscribe('/topic/btc-price', (message) => {
          const newPrice = parseFloat(message.body);
          setPrevPrice(price);
          setPrice(newPrice);
          if (price !== null) {
            setChange(newPrice - price);
          }
        });
      },
    });
    client.activate();
    return () => client.deactivate();
    // eslint-disable-next-line
  }, [price]);

  return (
    <div style={{ textAlign: 'center', marginTop: '10vh', fontFamily: 'Arial' }}>
      <h1>BTC/USDT Real-Time Price</h1>
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

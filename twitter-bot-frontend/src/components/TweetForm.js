import React, { useState } from 'react';
import { Box, Button, TextField } from '@mui/material';
import axios from 'axios';
import { API_BASE_URL } from '../config';

export default function TweetForm() {
  const [tweet, setTweet] = useState('');
  const [status, setStatus] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    setStatus('Sending...');
    try {
      const res = await axios.post(`${API_BASE_URL}/bot/tweet`, null, { params: { message: tweet } });
      setStatus(res.data);
      setTweet('');
    } catch (err) {
      setStatus('Error: ' + (err.response?.data || err.message));
    }
  };

  return (
    <Box mb={3}>
      <form onSubmit={handleSubmit}>
        <TextField
          label="What's happening?"
          variant="outlined"
          fullWidth
          value={tweet}
          onChange={e => setTweet(e.target.value)}
          inputProps={{ maxLength: 280 }}
        />
        <Button type="submit" variant="contained" color="primary" sx={{ mt: 2 }}>
          Tweet
        </Button>
      </form>
      {status && <Box mt={1}>{status}</Box>}
    </Box>
  );
}

import React from 'react';
import { Container, Typography, Box } from '@mui/material';
import TweetForm from './components/TweetForm';
import Timeline from './components/Timeline';
import BotLog from './components/BotLog';

function App() {
  return (
    <Container maxWidth="md">
      <Box my={4}>
        <Typography variant="h3" align="center" gutterBottom>
          Twitter Bot Dashboard
        </Typography>
        <TweetForm />
        <Timeline />
        <BotLog />
      </Box>
    </Container>
  );
}

export default App;

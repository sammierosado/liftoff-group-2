import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from './components/HomePage';
import NavigationBar from './components/NavigationBar';


function App() {
  return (
  <Router>
    <div className="App">
        <NavigationBar />
    <Routes>
          <Route path="/" exact component={HomePage} />
    </Routes>

      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
    </Router>
  )
};

export default App;

import React, { useState } from 'react';
import './Login.css';
import avatarImage from './avatar.png';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../security/AuthContext';

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);

    const navigate = useNavigate();
    const { login } = useAuth();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/auth/login', {
                email: email,
                password: password
            });
    
            login(response.data);
    
            if (response.data.role === 'ROLE_USER') {
                navigate('/user');
            } else if (response.data.role === 'ROLE_ADMIN') {
                navigate('/admin');
            }
        } catch (error) {
            setError(error.response.data.message);
        }
    };

    return (
        <div className="login-page">
            <div className="login-container">
                <img src={avatarImage} alt="Avatar" className="avatar" />
                <h2>Login</h2>
                <form onSubmit={handleLogin}>
                    <div className="form-group">
                        <label>Email:</label>
                        <input
                            type="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label>Password:</label>
                        <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>
                    {error && <div className="error">{error}</div>}
                    <button type="submit">Login</button>
                    <div className="register-link">
                        <p>Don't have an account yet? <Link to="/register">Register here</Link></p>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default Login;

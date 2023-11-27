import React, { useState, useEffect } from 'react';
import './UserModal.css';
import axios from 'axios';

const UserModal = ({ isOpen, onClose, mode, userData, onSubmit }) => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null); // Added state for error

    useEffect(() => {
        if (mode === 'Edit' && userData) {
            setFirstName(userData.firstName);
            setLastName(userData.lastName);
            setEmail(userData.email);
        } else {
            setFirstName('');
            setLastName('');
            setEmail('');
            setPassword('');
        }
    }, [isOpen, mode, userData]);

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!userData) {
            const newUserData = { firstName, lastName, email, password };

            try {
                const response = await axios.post('http://localhost:8080/users/create', newUserData);
                console.log('User created:', response.data);
                onSubmit(newUserData);
            } catch (error) {
                setError(error.response.data);
                console.error('Error:', error);
            }
        } else {
            const updatedUserData = { id: userData.id, firstName, lastName, email, password };

            try {
                const response = await axios.put(`http://localhost:8080/users/update/${userData.id}`, updatedUserData);
                console.log('User updated:', response.data);
                onSubmit(updatedUserData);
            } catch (error) {
                setError(error.response.data);
                console.error('Error:', error);
            }
        }
    };

    return (
        <div className={`modal ${isOpen ? 'open' : ''}`}>
            <div className="modal-content">
                <h2>{`${mode === 'Edit' ? 'Edit' : 'Add'} User`}</h2>
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label>First Name:</label>
                        <input
                            type="text"
                            value={firstName}
                            onChange={(e) => setFirstName(e.target.value)}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label>Last Name:</label>
                        <input
                            type="text"
                            value={lastName}
                            onChange={(e) => setLastName(e.target.value)}
                            required
                        />
                    </div>
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
                    <button type="submit">{`${mode === 'Edit' ? 'Edit' : 'Create'} User`}</button>
                    <button type="button" className="cancel" onClick={onClose}>Cancel</button>
                </form>
            </div>
        </div>
    );
};

export default UserModal;

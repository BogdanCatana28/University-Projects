import React, { useState, useEffect } from 'react';
import './DeviceModal.css';
import axios from 'axios';

const DeviceModal = ({ isOpen, onClose, mode, deviceData, onSubmit }) => {
    const [model, setModel] = useState('');
    const [userId, setUserId] = useState('');
    const [type, setType] = useState('');
    const [error, setError] = useState(null);

    useEffect(() => {
        if (mode === 'Edit' && deviceData) {
            setModel(deviceData.model);
            setUserId(deviceData.userId);
            setType(deviceData.type);
        } else {
            setModel('');
            setUserId('');
            setType('');
        }
    }, [isOpen, mode, deviceData]);

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!deviceData) {
            const newDeviceData = { model, userId, type };

            try {
                const response = await axios.post('http://localhost:8081/devices/create', newDeviceData);
                console.log('Device created:', response.data);
                onSubmit(newDeviceData);
            } catch (error) {
                setError(error.response.data);
                console.error('Error:', error);
            }
        } else {
            const updatedDeviceData = { id: deviceData.id, model, type, userId };
            console.log(updatedDeviceData);

            try {
                const response = await axios.put(`http://localhost:8081/devices/update/${deviceData.deviceId}`, updatedDeviceData);
                console.log('Device updated:', response.data);
                onSubmit(updatedDeviceData);
            } catch (error) {
                setError(error.response.data);
                console.error('Error:', error);
            }
        }
    };

    return (
        <div className={`modal ${isOpen ? 'open' : ''}`}>
            <div className="modal-content">
                <h2>{`${mode === 'Edit' ? 'Edit' : 'Add'} Device`}</h2>
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label>Model:</label>
                        <input
                            type="text"
                            value={model}
                            onChange={(e) => setModel(e.target.value)}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label>Owner:</label>
                        <input
                            type="text"
                            value={userId}
                            onChange={(e) => setUserId(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Type:</label>
                        <select
                            value={type}
                            onChange={(e) => setType(e.target.value)}
                            required
                        >
                            <option value="">Select Type</option>
                            <option value="LAPTOP">Laptop</option>
                            <option value="COMPUTER">Computer</option>
                            <option value="PHONE">Phone</option>
                            <option value="TABLET">Tablet</option>
                            <option value="SMARTWATCH">Smartwatch</option>
                        </select>
                    </div>
                    {error && <div className="error">{error}</div>}
                    <button type="submit">{`${mode === 'Edit' ? 'Edit' : 'Create'} Device`}</button>
                    <button type="button" className="cancel" onClick={onClose}>Cancel</button>
                </form>
            </div>
        </div>
    );
};

export default DeviceModal;

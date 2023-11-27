import React, { useState } from 'react';
import axios from 'axios';
import { useAuth } from '../security/AuthContext';

const UserPage = () => {
    const [devices, setDevices] = useState([]);
    const [searchText, setSearchText] = useState('');
    const [deviceError, setDeviceError] = useState('');

    const { user } = useAuth();
    console.log(user);

    if (!user || user.role !== 'ROLE_USER') {
        return <div>Unauthorized Access</div>;
    }

    const fetchDevices = async () => {
        try {
            const response = await axios.get(`http://localhost:8081/devices/get_by_user/${user.id}`);
            setDevices(response.data);
            setDeviceError('');
        } catch (error) {
            setDeviceError(error.response.data);
            setDevices([]);
        }
    };

    const handleSearchDevice = async (e) => {
        e.preventDefault();

        try {
            if (searchText.trim() === '') {
                fetchDevices();
            } else {
                const response = await axios.get(`http://localhost:8081/devices/get_by_user/${user.id}`);
                const filteredDevices = response.data.filter(device =>
                    device.model.toLowerCase().includes(searchText.toLowerCase()) ||
                    device.type.toLowerCase().includes(searchText.toLowerCase())
                );
                setDevices(filteredDevices);
            }

            setDeviceError('');
        } catch (error) {
            setDeviceError(error.response.data);
            setDevices([]);
        }
    };

    return (
        <div className="admin-page">
            <div className="admin-container">
                <h2>My Devices</h2>
                <div className="search-container">
                    <input
                        type="text"
                        value={searchText}
                        onChange={(e) => setSearchText(e.target.value)}
                        placeholder="Search"
                    />
                    <button onClick={handleSearchDevice}>Search</button>
                </div>
                {deviceError && <div className="error">{deviceError}</div>}
                <table className="user-table">
                    <thead>
                        <tr>
                            <th>Model</th>
                            <th>Type</th>
                        </tr>
                    </thead>
                    <tbody>
                        {devices.map((device) => (
                            <tr key={device.id}>
                                <td>{device.model}</td>
                                <td>{device.type}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default UserPage;

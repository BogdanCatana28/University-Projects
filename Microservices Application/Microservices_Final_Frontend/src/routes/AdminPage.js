import React, { useState } from 'react';
import './AdminPage.css';
import axios from 'axios';
import UserModal from '../components/UserModal';
import DeviceModal from '../components/DeviceModal';
import { useAuth } from '../security/AuthContext';

const AdminPage = () => {
    const [searchTextUser, setSearchTextUser] = useState('');
    const [searchTextDevice, setSearchTextDevice] = useState('');
    const [users, setUsers] = useState([]);
    const [devices, setDevices] = useState([]);
    const [userError, setUserError] = useState('');
    const [deviceError, setDeviceError] = useState('');
    const [showModal, setShowModal] = useState(false);
    const [modalMode, setModalMode] = useState('Add');
    const [selectedUser, setSelectedUser] = useState(null);
    const [showDeviceModal, setShowDeviceModal] = useState(false);
    const [deviceModalMode, setDeviceModalMode] = useState('Add');
    const [selectedDevice, setSelectedDevice] = useState(null);

    const { user } = useAuth();

    if (!user || user.role !== 'ROLE_ADMIN') {
        return <div>Unauthorized Access</div>;
    }

    const fetchUsers = async () => {
        try {
            const response = await axios.get('http://localhost:8080/users/get_all');
            setUsers(response.data);
            setUserError('');
        } catch (error) {
            setUserError(error.response.data);
            setUsers([]);
        }
    };

    const fetchDevices = async () => {
        try {
            const response = await axios.get('http://localhost:8081/devices/get_all');
            setDevices(response.data);
            setDeviceError('');
        } catch (error) {
            setDeviceError(error.response.data);
            setDevices([]);
        }
    };

    const handleAddUser = () => {
        setModalMode('Add');
        setShowModal(true);
    };

    const handleEditUser = (user) => {
        setModalMode('Edit');
        setSelectedUser(user);
        setShowModal(true);
    };

    const handleCloseModal = () => {
        setShowModal(false);
        setModalMode('Add');
        setSelectedUser(null);
    };

    const handleSubmitModal = (userData) => {
        handleCloseModal();
        fetchUsers();
    };

    const handleAddDevice = () => {
        setDeviceModalMode('Add');
        setShowDeviceModal(true);
    };

    const handleEditDevice = (device) => {
        setDeviceModalMode('Edit');
        setSelectedDevice(device);
        setShowDeviceModal(true);
    };

    const handleCloseDeviceModal = () => {
        setShowDeviceModal(false);
        setDeviceModalMode('Add');
        setSelectedDevice(null);
    };

    const handleSubmitDeviceModal = (deviceData) => {
        handleCloseDeviceModal();
        fetchDevices();
    };

    const handleSearchUser = async (e) => {
        e.preventDefault();

        try {
            if (searchTextUser.trim() === '') {
                fetchUsers();
            } else {
                const response = await axios.get('http://localhost:8080/users/get_all');
                const filteredUsers = response.data.filter(user =>
                    user.firstName.toLowerCase().includes(searchTextUser.toLowerCase()) ||
                    user.lastName.toLowerCase().includes(searchTextUser.toLowerCase())
                );
                setUsers(filteredUsers);
            }

            setUserError('');
        } catch (error) {
            setUserError(error.response.data);
            setUsers([]);
        }
    };

    const handleSearchDevice = async (e) => {
        e.preventDefault();

        try {
            if (searchTextDevice.trim() === '') {
                fetchDevices();
            } else {
                const response = await axios.get('http://localhost:8081/devices/get_all');
                const filteredDevices = response.data.filter(device =>
                    device.model.toLowerCase().includes(searchTextDevice.toLowerCase()) ||
                    device.type.toLowerCase().includes(searchTextDevice.toLowerCase())
                );
                setDevices(filteredDevices);
            }

            setDeviceError('');
        } catch (error) {
            setDeviceError(error.response.data);
            setDevices([]);
        }
    };

    const handleDeleteUser = async (id) => {
        try {
            const response = await axios.delete(`http://localhost:8080/users/delete/${id}`);
            console.log(response.data);
            fetchUsers();
            fetchDevices();
        } catch (userError) {
            console.error('Error:', userError);
        }
    };

    const handleDeleteDevice = async (deviceId) => {
        try {
            const response = await axios.delete(`http://localhost:8081/devices/delete/${deviceId}`);
            console.log(response.data);
            fetchDevices();
        } catch (deviceError) {
            console.error('Error:', deviceError);
        }
    };

    return (
        <div className="admin-page">
            <div className="admin-container">
                <h2>Users CRUD</h2>
                <div className="search-container">
                    <input
                        type="text"
                        value={searchTextUser}
                        onChange={(e) => setSearchTextUser(e.target.value)}
                        placeholder="Search"
                    />
                    <button onClick={handleSearchUser}>Search</button>
                    <button onClick={handleAddUser}>Add User</button>
                </div>
                {userError && <div className="error">{userError}</div>}
                <table className="user-table">
                    <thead>
                        <tr>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {users.map((user) => (
                            <tr key={user.id}>
                                <td>{user.firstName}</td>
                                <td>{user.lastName}</td>
                                <td>{user.email}</td>
                                <td>
                                    <button className="edit-button" onClick={() => handleEditUser(user)}>Edit</button>
                                    <button className="delete-button" onClick={() => handleDeleteUser(user.id)}>Delete</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>

                <h2>Devices CRUD</h2>
                <div className="search-container">
                    <input
                        type="text"
                        value={searchTextDevice}
                        onChange={(e) => setSearchTextDevice(e.target.value)}
                        placeholder="Search"
                    />
                    <button onClick={handleSearchDevice}>Search</button>
                    <button onClick={handleAddDevice}>Add Device</button>
                </div>
                {deviceError && <div className="error">{deviceError}</div>}
                <table className="user-table">
                    <thead>
                        <tr>
                            <th>Model</th>
                            <th>Type</th>
                            <th>Owner</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {devices.map((device) => (
                            <tr key={device.id}>
                                <td>{device.model}</td>
                                <td>{device.type}</td>
                                <td>{device.userId}</td>
                                <td>
                                    <button className="edit-button" onClick={() => handleEditDevice(device)}>Edit</button>
                                    <button className="delete-button" onClick={() => handleDeleteDevice(device.deviceId)}>Delete</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
            <UserModal
                isOpen={showModal}
                onClose={handleCloseModal}
                mode={modalMode}
                userData={selectedUser}
                onSubmit={handleSubmitModal}
            />
            <DeviceModal
                isOpen={showDeviceModal}
                onClose={handleCloseDeviceModal}
                mode={deviceModalMode}
                deviceData={selectedDevice}
                onSubmit={handleSubmitDeviceModal}
            />
        </div>
    );
};

export default AdminPage;

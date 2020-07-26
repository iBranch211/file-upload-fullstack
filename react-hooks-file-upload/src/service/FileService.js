import axios from "axios"

const upload = (file, onUploadProgress) => {
    let formData = new FormData();

    const config = {
        headers: {"Content-Type": "multipart/form-data"},
        onUploadProgress
    }

    formData.append("file", file);

    return axios.post("/api/versions/1/files/upload", formData, config);
};

const getFiles = () => {
    return axios.get("/api/versions/1/files");
};

export default {
    upload,
    getFiles,
};
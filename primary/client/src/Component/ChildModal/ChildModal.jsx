import { Box, Modal } from "@mui/material";
import "./ChildModal.css";
import React, { useState } from "react";

function ChildModal({ header, tnc }) {

  const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    pt: 2,
    px: 4,
    pb: 3,
  };

  const [open, setOpen] = useState(false);
  const handleOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };
  return (
    <>
      <button className="underline text-blue-700" onClick={handleOpen} >
        {header}
      </button>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="child-modal-title"
        aria-describedby="child-modal-description"
      >
        <Box sx={{ ...style, width: 800 }}>
          <p id="font-bold">{header}</p>
          <p id="">
            {tnc}
          </p>
        </Box>
      </Modal>
    </>
  );
};

export default ChildModal;

const fs = require('fs');
const path = require('path');

const imgPath = path.join(__dirname, 'src/assets/player/kiwi_run.png');
const buf = fs.readFileSync(imgPath);

// PNG header check and read dimensions
if (buf[0] === 0x89 && buf[1] === 0x50 && buf[2] === 0x4E && buf[3] === 0x47) {
    const width = buf.readUInt32BE(16);
    const height = buf.readUInt32BE(20);
    console.log('Image dimensions:');
    console.log('  Width:', width);
    console.log('  Height:', height);
    console.log('');
    console.log('Current code expects:');
    console.log('  IMAGE_WIDTH: 500');
    console.log('  IMAGE_HEIGHT: 500');
    console.log('');
    if (width !== 500 || height !== 500) {
        console.log('*** MISMATCH DETECTED! ***');
        console.log('Suggested values:');
        console.log('  const IMAGE_WIDTH =', width + ';');
        console.log('  const IMAGE_HEIGHT =', height + ';');
        console.log('  const FRAME_WIDTH =', Math.floor(width / 5) + ';');
        console.log('  const FRAME_HEIGHT = IMAGE_HEIGHT / ROWS;');
    } else {
        console.log('Dimensions match! Image size is correct.');
    }
} else {
    console.log('Not a valid PNG file');
}


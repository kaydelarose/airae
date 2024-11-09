import React from 'react';
import { Line } from 'react-chartjs-2';

interface UVIndexChartProps {
    uvIndexes: number[];
    times: string[];
}

const UVIndexChart: React.FC<UVIndexChartProps> = ({ uvIndexes, times }) => {
    const data = {
        labels: times,
        datasets: [
            {
                label: 'UV Index',
                data: uvIndexes,
                borderColor: 'rgba(255, 99, 132, 1)',
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                fill: true,
            },
        ],
    };

    const options = {
        scales: {
            y: { beginAtZero: true, title: { display: true, text: 'UV Index' } },
            x: { title: { display: true, text: 'Time' } },
        },
    };

    return <Line data={data} options={options} />;
};

export default UVIndexChart;

import React, { useState } from 'react';
import './Filter.css';

interface FilterProps {
  onFilterChange: (filter: { skinType?: string; concern?: string; fragrance?: boolean }) => void;
}

const Filter = ({ onFilterChange }: FilterProps) => {
  const [selectedSkinType, setSelectedSkinType] = useState('');
  const [selectedConcern, setSelectedConcern] = useState('');
  const [fragranceFree, setFragranceFree] = useState(false);

  const handleApplyFilters = () => {
    onFilterChange({
      skinType: selectedSkinType,
      concern: selectedConcern,
      fragrance: fragranceFree,
    });
  };

  return (
    <div className="filter-container">
      <h5 className="filter-title">Filter Products</h5>
      <hr />

      <div className="filter-group">
        <label className="filter-label">Skin Type</label>
        <select
          className="form-select filter-select"
          value={selectedSkinType}
          onChange={(e) => setSelectedSkinType(e.target.value)}
        >
          <option value="">All Skin Types</option>
          <option value="normal">Normal</option>
          <option value="dry">Dry</option>
          <option value="oily">Oily</option>
          <option value="sensitive">Sensitive</option>
          <option value="combination">Combination</option>
        </select>
      </div>

      <div className="filter-group">
        <label className="filter-label">Concerns</label>
        <select
          className="form-select filter-select"
          value={selectedConcern}
          onChange={(e) => setSelectedConcern(e.target.value)}
        >
          <option value="">All Concerns</option>
          <option value="hydration">Hydration</option>
          <option value="acne">Acne</option>
          <option value="redness">Redness</option>
          <option value="anti-aging">Anti-Aging</option>
          <option value="oil-control">Oil Control</option>
        </select>
      </div>

      <div className="filter-group">
        <div className="form-check">
          <input
            type="checkbox"
            className="form-check-input"
            id="fragranceFree"
            checked={fragranceFree}
            onChange={(e) => setFragranceFree(e.target.checked)}
          />
          <label className="form-check-label" htmlFor="fragranceFree">
            Fragrance-Free
          </label>
        </div>
      </div>

      <button className="btn btn-apply-filter" onClick={handleApplyFilters}>
        Apply Filters
      </button>
    </div>
  );
};

export default Filter;

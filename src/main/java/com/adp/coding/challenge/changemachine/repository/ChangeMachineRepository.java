package com.adp.coding.challenge.changemachine.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.adp.coding.challenge.changemachine.model.Coin;

@Repository
public class ChangeMachineRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;

	class CoinRowMapper implements RowMapper<Coin> {
		@Override
		public Coin mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Coin(rs.getDouble("value"), rs.getDouble("count"));
		}

	}

	public List<Coin> getAvailableCoins() {
		return jdbcTemplate.query("select value,count from coin order by value desc", new CoinRowMapper());
	}

	@Transactional
	public void updateCoins(List<Coin> coins) {
		for (Coin c : coins) {
			jdbcTemplate.update("update coin " + " set count = ? " + " where value = ?",
					new Object[] { c.getCount(), c.getCoinValue() });
		}
	}
}
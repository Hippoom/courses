package com.github.hippoom.courses.pt.anemic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class JdbcPendingOrderDao extends JdbcDaoSupport {

	public void save(PendingOrderDto order) {
		getJdbcTemplate()
				.update("INSERT INTO t_f2g_pending_order(tracking_id, delivery_address_street1, delivery_address_street2, delivery_time) VALUES (?,?,?,?)",
						new Object[] { order.getTrackingId(),
								order.getDeliveryAddressStreet1(),
								order.getDeliveryAddressStreet2(),
								order.getDeliveryTime() },
						new int[] { Types.VARCHAR, Types.VARCHAR,
								Types.VARCHAR, Types.DATE });
	}

	public PendingOrderDto findBy(String trackingId) {
		return getJdbcTemplate().queryForObject(
				"select * from t_f2g_pending_order where tracking_id = ?",
				new RowMapper<PendingOrderDto>() {

					public PendingOrderDto mapRow(ResultSet resultSet, int arg1)
							throws SQLException {
						PendingOrderDto order = new PendingOrderDto();
						order.setTrackingId(resultSet.getString("tracking_id"));
						order.setDeliveryAddressStreet1(resultSet
								.getString("delivery_address_street1"));
						order.setDeliveryAddressStreet2(resultSet
								.getString("delivery_address_street2"));
						order.setDeliveryTime(resultSet
								.getTimestamp("delivery_time"));
						order.setRestaurantId(resultSet
								.getString("restaurant_id"));
						return order;
					}

				}, trackingId);

	}

	public void update(PendingOrderDto order) {
		getJdbcTemplate()
				.update("update t_f2g_pending_order set restaurant_id = ? where tracking_id = ?",
						new Object[] { order.getRestaurantId(),
								order.getTrackingId() },
						new int[] { Types.VARCHAR, Types.VARCHAR });

	}
}

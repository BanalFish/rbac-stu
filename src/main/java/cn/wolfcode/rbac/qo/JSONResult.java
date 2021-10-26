package cn.wolfcode.rbac.qo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JSONResult {
	private boolean success = true;
	private String msg = "成功";
	private Object result;//成功之后返回给前台的数据

	public JSONResult(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public void mark(String msg) {
		this.success = false;
		this.msg = msg;
	}
}

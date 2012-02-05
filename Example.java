public class Example {

	public static void main(String[] args) {
		ProwlClient c = new ProwlClient();
		ProwlEvent e = new DefaultProwlEvent(
				"myKey", "application", "event",
				"message", 0, "customLaunchUrl");
		String message;
		try {
			message = c.pushEvent(e);
			System.out.println(message);
		} catch (ProwlException e1) {
			e1.printStackTrace();
		}
	}
}

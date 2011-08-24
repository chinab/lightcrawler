package org.las.tools;

public abstract class SafeContainer<T> {

	private T object = null;
	
	private static final long MAX_MILLISTIME = 3000;
	
	protected abstract T inBox();
	
	public SafeContainer(T object) {
		this.object = object;
	}
	
	public T execute(){
		Thread thread = new Thread(){
			public void run(){
				try {
					T t = inBox();
					if(t!=null){
						object = t;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		try {
			thread.start();
			thread.join(MAX_MILLISTIME);
			thread.interrupt();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("-!SafeContaner Interrupted Dead Thread!");
		}
		return object;
	}
	
	
}

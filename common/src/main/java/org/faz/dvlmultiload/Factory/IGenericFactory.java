package org.faz.dvlmultiload.Factory;

public interface IGenericFactory<T>
{
    T create();
    T create(int cap);
}
